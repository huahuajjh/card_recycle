var fileSize = (value) => {
  if(value < 1024) {
    return value + "B";
  } else if(value < 1024 * 1024) {
    return (value / 1024).toFixed(0) + "KB";
  } else if(value < 1024 * 1024 * 1024) {
    return (value / (1024 * 1024)).toFixed(0) + "MB";
  } else if(value < 1024 * 1024 * 1024 * 1024) {
    return (value / (1024 * 1024 * 1024)).toFixed(0) + "GB";
  } else {
    return (value / (1024 * 1024 * 1024 * 1024)).toFixed(0) + "TB";
  }
}

export default fileSize;

export var install = (Componenet) => {
  Componenet.filter("fileSize", fileSize);
}