window.NewMessageFormView = Backbone.View.extend({

    initialize: function () {
        $("new-message-form").submit(function (event) {
            event.preventDefault();
            return false;
        });
    },

    el: "div#container",

    template: _.template($('#new-message-form-template').html()),

    render: function () {
        this.$el.html(this.template());
        return this;
    },

    events: {
        "click .post": "postNewMessage",
        "click .return": "previousPage"
    },

    postNewMessage: function () {
        var values = this.getFormInputs();
        var newMessage = new Message(values);
        if (!this.isFormEmpty()) {
            var context = this;
            newMessage.save({},{
                success: function() {
                    context.cleanup();
                    Materialize.toast("Message envoyé",3000);
                    router.navigate("messages", {trigger: true});
                }
            });
        } else {
            Materialize.toast("Ce message est vide !",3000);
        }
        this.cleanup;
    },

    isFormEmpty: function () {
        return _.isEmpty($('textarea').val());
    },

    getFormInputs: function () {
        return {
            content: $('textarea').val(),
            url: $('#icon_link').val(),
            img: $('#icon_img').val()
        }
    },

    previousPage: function () {
        this.cleanup();
        window.router.navigate("/", {trigger: true});
    },
    
    cleanup: function() {
        this.undelegateEvents();
        this.$el.html();
    }

});
