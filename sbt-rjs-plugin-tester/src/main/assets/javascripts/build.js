/*global alert, require, requirejs */

requirejs.config({
    paths: {
        'underscore': '../lib/underscorejs/underscore',
        myrequire: '../lib/requirejs/require'
    },
    shim: {
        'underscore': {
            exports: '_'
        }
    }
});