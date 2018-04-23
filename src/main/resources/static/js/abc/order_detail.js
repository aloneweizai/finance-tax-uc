require(["../config"], function () {
    require(["jquery.full"], function ($) {
        var iframet = parent.document.getElementById("external-frame");
        $(iframet).height(1180);
    })
})