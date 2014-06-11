/*global alert, require, requirejs */

require(["./b", "underscore"], function (b, _) {
    _.each([b], alert);
});