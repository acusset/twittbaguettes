/**
 * Created by Antoine on 20/06/2016.
 */

var MessagesView = Backbone.View.extend({

    model: MessagesCollection,

    el: "div#container",

    render: function (model) {

        this.$el.html(); // lets render this view

        var self = this;

        for (var i = 0; i < this.model.length; ++i) {
            var messageView = new MessageView({model: this.model.at(i)});

            this.$el.append(messageView.$el);
            messageView.render();
        }
        return this;
    },

});