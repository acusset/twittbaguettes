var UserProfileView = Backbone.View.extend({

    el: "div#container",

    template: _.template($('#user-template').html()),
    model: User,

    render: function () {
        this.$el.html('');
        this.$el.html(this.template(this.model.attributes));
        return this;
    },

    events: {
        "click .delete": "deleteUser",
        "click .edit": "editUser"
    },

    deleteUser: function () {
        this.$el.attr("class", this.className + " animated fadeOutLeft");
        var context = this;
        setTimeout(function () {
            context.$el.html("");
            context.model.destroy();
            Materialize.toast("Utilisateur supprimé",3000);
        }, 1300);
    },

    editUser: function () {
        var url = "u/" + this.model.get("id") + "/edit";
        window.router.navigate(url, {trigger: true});
    }
});