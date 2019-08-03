var axiosLib = axios.create({
    baseURL: "https://restcountries-v1.p.rapidapi.com/",
    headers: {
        'RapidAPI-Project': "default-application_3853138",
        "X-RapidAPI-Host": "restcountries-v1.p.rapidapi.com",
        "X-RapidAPI-Key": "d2d416ff10msh9b832b7f38ce91ap119c78jsn80c43585a0bd"
    }
});

var app = new Vue({
    el: "#app",
    data: {
        t2: "",
        langName: "",
        requestResult: ""
    },
    computed: {
        t1 () {
            var t2AsNumber = +this.t2;
            if (typeof t2AsNumber != "number" || isNaN(t2AsNumber))
                return "Введите число!";
            return this.t2 * 2 + (t2AsNumber == 0 ? 0 : 3);
        }
    },
    methods: {
        printCountries: function () {
            getCountriesByLanguageSt(this.langName);
            // this.requestResult = jsonPrettyStringify({
            //     data: [
            //         {
            //             name: "ogrezem",
            //             password: "wdkawopdpm",
            //             email: "ogrezem@gmail.com"
            //         },
            //         {
            //             name: "alex",
            //             password: "12345",
            //             email: "nigosh@mail.ru"
            //         }
            //     ]
            // });
        }
    }
});

const vueResourceConfig = {
    headers: {
        'RapidAPI-Project': "default-application_3853138",
        "X-RapidAPI-Host": "restcountries-v1.p.rapidapi.com",
        "X-RapidAPI-Key": "d2d416ff10msh9b832b7f38ce91ap119c78jsn80c43585a0bd"
    }
};

function getCountriesByLanguage(language) {
    return axiosResponseToJson(getCountriesByLanguageSt(language))
}

const countriesApiPath = "https://restcountries-v1.p.rapidapi.com";

function getCountriesByLanguageSt(language) {
    Vue.http.get(countriesApiPath + "/lang/" + language, vueResourceConfig).then(response => {
        app.requestResult = jsonPrettyStringify(response.body);
    }, response => {
        app.requestResult = "Языка с таким кодом не найдено";
        console.log(response.statusText)
    });
}

function getCountriesByLanguageAxios(language) {
    return axiosLib.get("/lang/" + language).then(function (response) {
        console.log("good");
    }).catch(function (error) {
        console.log(error)
    });
}

function axiosResponseToJson(response) {
    return jsonPrettyStringify({data: response.data})
}

function jsonPrettyStringify(json) {
    return JSON.stringify(json, null, 2);
}
