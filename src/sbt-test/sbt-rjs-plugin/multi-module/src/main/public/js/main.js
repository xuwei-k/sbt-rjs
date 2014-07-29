requirejs.config({
  paths: {
    'a': '../lib/a/js/a',
    'b': '../lib/b/js/b'
  }
});

require(["a"], function(a){
  console.log(a());
});
