var app = new Vue({
    el: "#app",
    data: {
        t2: ""
    },
    computed: {
        t1: function () {
            var t2AsNumber = +this.t2;
            if (typeof t2AsNumber != "number" || isNaN(t2AsNumber))
                return "Введите число!";
            return this.t2 * 2 + (t2AsNumber == 0 ? 0 : 3);
        }
    }
});



function getCountriesByLanguages(languages) {

}