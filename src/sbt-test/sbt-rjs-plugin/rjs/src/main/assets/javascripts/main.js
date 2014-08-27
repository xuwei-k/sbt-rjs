requirejs.config({
    paths: {
        'myunderscore': '../lib/underscorejs/underscore',
        myknockout: '../lib/knockout/knockout',
        "myjquery": "../lib/jquery/jquery"
    },
    shim: {
        'underscore': {
            exports: '_'
        }
    }
});

require(["./a"], function() {

});