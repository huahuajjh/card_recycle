import StateMan from 'stateman';
import Promise from 'mmPromise';

var _ = StateMan.util;
// get all state match the pattern
function getMatchStates(stateman, pattern) {
  var current = stateman;
  var allStates = [];
  var currentStates = current._states;
  for (var i in currentStates) {
    var state = currentStates[i];
    if (pattern.test(state.stateName)) allStates.push(state);
    if (state._states) allStates = allStates.concat(getMatchStates(state, pattern))
  }
  return allStates
}

function getComponentObj(Component, data, stateman, name) {
  var filters = {
    encode: function (value, param) {
      return stateman.history.prefix + (stateman.encode(value, param || {}) || "");
    }
  }
  if (!Component.filter("encode")) {
    Component.filter(filters);
  }
  var component = new Component({
    data: data,
    $state: stateman,
    $stateName: name,
    /**
     * notify other module
     * @param  {String} stateName module's stateName
     *         you can pass wildcard(*) for 
     *       
     * @param  {Whatever} param   event param
     * @return {Component} this 
     */
    $notify: function (stateName, type, param) {
      var pattern, eventObj, state;
      if (!stateName) return;
      if (~stateName.indexOf('*')) {
        pattern = new RegExp(
          stateName
            .replace('.', '\\.')
            .replace(/\*\*|\*/, function (cap) {
              if (cap === '**') return '.*';
              else return '[^.]*';
            })
        );
        getMatchStates.forEach(function (state) {
          if (state.component) state.component.$emit(type, {
            param: param,
            from: name,
            to: state.stateName
          })
        })
      } else {
        state = stateman.state(stateName);
        if (!state || !state.component) return
        state.component.$emit(type, {
          param: param,
          from: name,
          to: stateName
        })
      }
    }
  });
  return component;
}

var restate = function (option) {
  option = option || {};
  var stateman = option.stateman || new StateMan(option);
  var preState = stateman.state;
  var globalView = option.view || document.body;
  stateman.state = function (name, Component, config) {
    if (typeof config === "string") {
      config = { url: config };
    }
    config = config || {};
    // Use global option.rebuild if config.rebuild is not defined.
    if (config.rebuild === undefined) config.rebuild = option.rebuild;
    if (!Component) return preState.call(stateman, name);
    var state = {
      component: null,
      // @TODO:
      canUpdate: function (option) {
        var canUpdate = this.component && this.component.canUpdate;
        if (canUpdate) return this.component.canUpdate(option);
      },
      canLeave: function (option) {
        var canLeave = this.component && this.component.canLeave;
        if (canLeave) return this.component.canLeave(option);
      },

      canEnter: function (option) {
        var data = { $param: option.param },
          component = this.component,
          // if component is not exist or required to be rebuilded when entering.
          noComponent = true,
          view;

        if (noComponent) {
          if( Component.extend && Component.__after__ ) {
            component = this.component = getComponentObj(Component, data, stateman, name);
          } else if(typeof Component === 'function'){
            var self = this;
            return new Promise(function(resolve) {
              Component(function(C) {
                component = self.component = getComponentObj(C, data, stateman, name);
                var canEnter = component && component.canEnter;
                if (canEnter) {
                  resolve(component.canEnter(option));
                } else {
                  resolve();
                }
              });
            });
          }
        }
        var canEnter = this.component && this.component.canEnter;
        if (canEnter) return this.component.canEnter(option);
      },

      enter: function (option) {
        var data = { $param: option.param };
        var component = this.component;
        var parent = this.parent, view;

        if (!component) return;

        _.extend(component.data, data, true);

        if (parent.component) {
          view = parent.component.$refs.view;
          if (!view) throw this.parent.name + " should have a element with [ref=view]";
        } else {
          view = globalView;
        }

        component.$inject(view);
        var result = component.enter && component.enter(option);

        component.$update(function () {
          component.$mute(false);
        })

        return result;
      },
      leave: function (option) {
        var component = this.component;
        if (!component) return;

        component.leave && component.leave(option);
        if (config.rebuild) {
          this.component = null;
          return component.destroy();
        }
        component.$inject(false);
        component.$mute(true);
      },
      update: function (option) {
        var component = this.component;
        if (!component) return;
        component.update && component.update(option);
        component.$update({
          $param: option.param
        })
      }
    }
    _.extend(state, config || {});

    preState.call(stateman, name, state);
    return this;
  }
  return stateman;
}

module.exports = restate;