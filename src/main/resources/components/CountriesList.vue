<template>
    <div id="app" class="container-fluid" style="margin-top: 10px">
        <div class="col" style="margin-bottom: 10px">
            <input v-model="langName" placeholder="Введите код языка">
        </div>
        <div class="col">
            <button class="btn btn-dark" @click="printCountries">Найти страны</button>
        </div>
        <div v-if="countriesAreBeingSearched" id="countries-loading-spinner" class="spinner-border" role="status"></div>
        <template v-else>
            <div id="countries-loading-error-message" class="text-center alert alert-danger"
                 v-if="errorMessageIsActive">
                Языка с таким кодом не найдено
            </div>
            <div class="col" v-else-if="requestResult !== ''">
                <pre class="text-left" v-highlightjs="requestResult">
                    <code id="request-result-code" class="json"></code>
                </pre>
            </div>
        </template>
    </div>
</template>

<script>
    import Vue from 'vue'

    export default {
        name: "countries-list",
        data() {
            return {
                langName: "",
                requestResult: "",
                countriesAreBeingSearched: false,
                errorMessageIsActive: false
            }
        },
        methods: {
            printCountries() {
                this.getCountriesByLanguageSt(this.langName)
            },
            getCountriesByLanguageSt(language) {
                this.countriesAreBeingSearched = true
                Vue.http.get(countriesApiPath + "/lang/" + language, vueResourceConfig).then(response => {
                    this.handleCountriesLoadingSuccess()
                    this.requestResult = jsonPrettyStringify(response.body)
                }).catch(response => {
                    this.handleCountriesLoadingError()
                    console.log(response.statusText)
                }).then(() => {
                    this.countriesAreBeingSearched = false;
                })
            },
            handleCountriesLoadingSuccess() {
                this.errorMessageIsActive = false
            },
            handleCountriesLoadingError() {
                this.errorMessageIsActive = true
                this.requestResult = ""
            }
        },
        created() {

        }
    }

    function jsonPrettyStringify(json) {
        return JSON.stringify(json, null, 2)
    }

    const countriesApiPath = "https://restcountries-v1.p.rapidapi.com"

    const vueResourceConfig = {
        headers: {
            'RapidAPI-Project': "default-application_3853138",
            "X-RapidAPI-Host": "restcountries-v1.p.rapidapi.com",
            "X-RapidAPI-Key": "d2d416ff10msh9b832b7f38ce91ap119c78jsn80c43585a0bd"
        }
    }
</script>

<style lang="sass">
    @import "../static/style/main"
    @import "../../../../node_modules/highlight.js/styles/dracula.css"

    #countries-loading-spinner
        margin-top: 20px

    #countries-loading-error-message
        margin-top: 20px

</style>