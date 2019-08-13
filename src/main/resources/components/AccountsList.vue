<template>
    <div>
        <account-entry v-for="account in accounts" :key="account.id" :account="account"/>
        <button @click="switchAllAccounts" class="btn btn-dark">Обновить список</button>
    </div>
</template>

<script>
    import Vue from 'vue'
    import AccountEntry from './AccountEntry'

    // const accountApi = Vue.resource('senderAccount{/username}')

    export default {
        name: "accounts-list",
        data() {
            return {
                accounts: {}
            }
        },
        methods: {
            switchAllAccounts() {
                Vue.http.get('http://localhost:8086/account')
                    .then(value => {
                        this.accounts = value.body
                        console.log(this.accounts)
                    }, error => {
                        console.log(error)
                    })
            }
        },
        components: {
            AccountEntry
        },
        created() {
            this.switchAllAccounts()
        }
    }
</script>

<style scoped lang="sass">
    @import "../static/style/main"
</style>