/**
 * Created by Jolan on 28/06/2016.
 */

var IndexView = Backbone.View.extend({

    el: '#container',

    initialize: function () {
        console.log("Initialisation de la vue par default");
        this.render();
    },

    render: function () {
        this.$el.html("<h1>Index View</h1>");
    }
});