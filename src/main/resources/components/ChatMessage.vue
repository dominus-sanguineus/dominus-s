<template>
    <div class="chat-message" :class="{'chat-message-text-right': isMessageSelf}">
        <!--    todo: добавить указание даты отправки сообщения с преобразованием в российский формат  -->
        <p class="username-text">{{ message.senderAccount.username }}</p>
        <p class="message-text">{{ message.text }}</p>
    </div>
</template>

<script>
    export default {
        name: "chat-message",
        props: {
            message: Object,
            isMessageSelf: Boolean
        }
    }
</script>

<style scoped lang="sass">
    .chat-message
        margin-bottom: 20px

        p
            margin: 0
            padding: 0 10px
            border-radius: 5px

        .username-text
            background-color: chartreuse
            display: inline-block

        .message-text
            background-color: azure
            padding-top: 10px
            padding-bottom: 10px

    .chat-message-text-right
        .username-text
            position: relative
            left: 100%
            transform: translateX(-100%)
</style>