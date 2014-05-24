requirejs.config({
    paths: {
        'myunderscore': '../lib/underscorejs/underscore'
    },
    shim: {
        'underscore': {
            exports: '_'
        }
    }
});

require(["./a"], function() {

});