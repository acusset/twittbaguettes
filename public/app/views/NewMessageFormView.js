window.NewMessageFormView = Backbone.View.extend({

    initialize: function () {
        $("new-message-form").submit(function () {
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
        "click button.envoyer": "postNewMessage"
    },

    postNewMessage: function () {
        var values = this.getFormInputs();
        var newMessage = new Message(values);
        if (!this.isFormEmpty()) {
            newMessage.save();
            router.navigate("messages", {trigger: true});
        }
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
    }

});
