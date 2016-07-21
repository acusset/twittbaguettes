var EditUserFormView = Backbone.View.extend({

    el: "div#container",

    id: function () {
        return this.model.get("id")
    },

    template: _.template($('#edit-user-form-template').html()),
    model: Message,

    render: function() {
        this.$el.html(this.template(this.model.attributes));
        return this;
    },

    events: {
        "click .post": "postUser",
        "click .return": "previousPage"
    },

    postUser: function () {
        var values = this.getFormInputs();
        var newMessage = new User(values);
        if (!this.isFormEmpty()) {
            var context = this;
            newMessage.save({},{
                success: function() {
                    context.cleanup();
                    Materialize.toast("Profil édité",3000);
                    router.navigate("me", {trigger: true});
                },
                error: function() {
                    Materialize.toast("Une erreure est survenue",3000);
                }
            });
        } else {
            Materialize.toast("Champ(s) invalide(s)",3000);
        }
    },

    isFormEmpty: function () {
        return _.isEmpty($("input[name=username]").val()) || _.isEmpty($("input[name=email]").val());
    },

    getFormInputs: function () {
        return {
            id: this.id(),
            username: $("input[name=username]").val(),
            password: $("input[name=password]").val(),
            email: $("input[name=email]").val(),
        }
    },

    previousPage: function () {
        this.cleanup();
        window.router.navigate("me", {trigger: true});
    },

    cleanup: function() {
        this.undelegateEvents();
        this.$el.html();
    }
});
