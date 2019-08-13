<template>
    <div class="chat-members-bar">
        <div v-for="chatMember in chatMembersList" class="chat-members-bar-entry"
             :class="chatMember.online ? 'chat-members-bar-entry-online' : 'chat-members-bar-entry-offline'">
            {{ chatMember.username  }}
        </div>
    </div>
</template>

<script>
    export default {
        name: "chat-members-bar",
        props: {
            chatMembersList: Array
        }
    }
</script>

<style scoped lang="sass">
    .chat-members-bar
        background-color: #ffe593
        height: 700px
        float: right
        overflow-y: auto

        .chat-members-bar-entry
            margin: 10px 5px
            padding: 5px 0
            border-radius: 5px
            text-align: center

        .chat-members-bar-entry-online
            background-color: chartreuse

        .chat-members-bar-entry-offline
            background-color: lightpink
</style>