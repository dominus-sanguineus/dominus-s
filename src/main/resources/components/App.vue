<template>
    <div>
        <basic-chat/>
<!--        <accounts-list/>-->
        <countries-list/>
    </div>
</template>

<script>
    import CountriesList from './CountriesList'
    import AccountsList from './AccountsList'
    import Vuex from 'vuex'
    import BasicChat from "./BasicChat";

    export default {
        name: "app",
        components: {
            BasicChat,
            CountriesList, AccountsList
        }
    }
</script>

<style scoped lang="sass">

</style>