window.LoginFormView = Backbone.View.extend({

    el: "div#container",

    template: _.template($('#login-form').html()),

    render: function( ) {
        this.$el.html(this.template());
        return this;
    }

});
