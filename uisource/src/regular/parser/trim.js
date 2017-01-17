export var install = (Componenet) => {
    Regular.filter('trim', {
        get: function(origin, split ){
            if(origin) return origin.trim();
            return "";
        },
        set: function(dest, split ){
            if(dest) return dest.trim();
            return "";
        }
    })
}