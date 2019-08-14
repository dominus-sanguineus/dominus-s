<template>
    <div id="basic-chat" class="container-fluid">
        <div id="chat-list" class="smooth-scroll">
            <template v-for="chatEntry in chatEntries">
                <chat-message v-if="chatEntry.senderAccount" :message="chatEntry"
                              :isMessageSelf="isMessageSelf(chatEntry, currentUserName)"/>
                <chat-notification v-else :text="chatEntry.text"/>
            </template>
            <div id="chat-list-bottom"></div>
        </div>
        <chat-members-bar :chatMembersList="chatMembersList"/>
        <div id="chat-control-panel">
            <textarea name="chat-list-text" id="chat-list-text" class="centralized"
                      placeholder="Введите сообщение" cols="35" rows="3" v-model="textForMessage"
                      @keyup.prevent.enter.exact="$refs.myBtn.click()"></textarea>
            <button id="send-message-button" class="btn btn-dark centralized" ref="myBtn"
                    @click="sendMessage(textForMessage, currentUserName)">Отправить</button>
        </div>
    </div>
</template>

<script>
    import ChatMessage from './ChatMessage'
    import ChatNotification from './ChatNotification'
    import ChatMembersBar from './ChatMembersBar'
    // const StompJs = require('@stomp/stompjs')
    import SockJs from 'sockjs-client'
    import Stomp from 'stomp-websocket'
    // import { Client, IFrame } from '@stomp/stompjs';
    // todo: сделать блокировку часа с колесом загрузки, пока клиент не подключится. В том числе нужно блокировать ввод
    export default {
        name: "basic-chat",
        data() {
            return {
                chatEntries: [
                    {
                        text: "привет! Как у тебя делишки?))",
                        senderAccount: {
                            username: "ogrezem"
                        }
                    },
                    {
                        text: "я думаю, что... пошёл нахуй!",
                        senderAccount: {
                            username: "stupid idiot"
                        }
                    }
                ],
                chatMembersList: [
                    {
                        username: "ogrezem",
                        online: true
                    },
                    {
                        username: "nigosh",
                        online: false
                    },
                    {
                        username: "gogs",
                        online: true
                    }
                ],
                currentUserName: "cherv" + userId,
                textForMessage: ""
            }
        },
        methods: {
            sendMessage(text, senderName) {//////////
                if (!text.trim()) {
                    this.textForMessage = ""
                    return
                }
                let messageObject = {
                    text: text.trim(),
                    senderAccount: {
                        username: senderName
                    }
                }
                if (!commandMode || !isCommand(text))
                    this.sendMessageToServer(messageObject)
                else
                    execCommand(text)
                // this.appendMessageToChat(messageObject)
                this.textForMessage = ""
            },
            scrollToChatBottom() {
                let chatBottomElement = document.getElementById('chat-list-bottom')
                chatBottomElement.scrollIntoView(false)
            },
            sendMessageToServer(messageObj) {
                client.send('/app/general/send', {}, JSON.stringify(messageObj))
            },
            appendMessageToChat(messageObj) {
                this.chatEntries.push(messageObj)
                this.scrollToChatBottom()
                new Promise(() => {
                    // ужасный костыль, который надо убрать
                    setTimeout(() => {
                        this.scrollToChatBottom()
                    }, 100)
                }).then()
            },
            isMessageSelf(message, currentUserName) {
                return message.senderAccount.username === currentUserName
            }
        },
        components: {
            ChatMessage, ChatNotification, ChatMembersBar
        },
        created() {
            const getSessionId = function(socket) {
                return socket._transport.url.match(/.*\/(.*)\/websocket/)[1]
            }
            var sessionId;
            client.connect({}, (frame) => {
                console.log("connected: " + frame.body)
                sessionId = getSessionId(socket)
                client.subscribe('/user/queue/main', message => {
                    const parsedMessage = JSON.parse(message.body);
                    // console.log("MESSAGE: " + parsedMessage)
                    console.log('Message text: ' + parsedMessage.text)
                    console.log('Sender username: ' + parsedMessage.senderAccount.username)
                    this.appendMessageToChat(parsedMessage)
                })
                client.subscribe('/user/queue/notification', notification => {
                    this.appendMessageToChat(JSON.parse(notification.body))
                })
                const userBody = {
                    id: "228",
                    username: "cherv" + userId,
                    password: "koks1337"
                }
                setTimeout(() => {
                    client.send('/app/general/register', {}, JSON.stringify(userBody))
                }, 1000)
            })
        }
    }

    let userId = Math.round(Math.random() * 100)
    const socket = new SockJs("https://localhost/ogr-ws")
    const client = Stomp.over(socket);

    const commandsList = [
        '/add message ', '/add user '
    ]

    var commandMode = false;

    function isCommand(text) {
        return commandsList.filter(value => text.startsWith(value)).length !== 0
    }

    function execCommand(command) {

    }

    // const client = new StompJs.Client({
    //     brokerURL: "ws://localhost:8086/ogr-ws",
    //     debug(str) {
    //         console.log("STOMP: " + str)
    //     },
    //     onConnect(frame) {
    //         console.log("connected! " + frame.body)
    //         console.log("Headers: " + JSON.stringify(frame.headers))
    //         console.log("URL: " + client.url)
    //         console.log("HH: " + JSON.stringify(client.webSocket.url))
    //
    //         // client.debug(frame.body)
    //     },
    //     reconnectDelay: 5000
    // })
    // client.onDisconnect((frame) => {
    //     console.log("disconnected!")
    // })
    // client.onStompError((frame) => {
    //     console.log("some stomp error!")
    // })
    // // client.subscribe("", (message) => {
    // //
    // client.activate()
    // // })
    // setTimeout(() => {
    //     const messageBody = {
    //         id: "228",
    //         username: "nigosh",
    //         password: "koks1337"
    //     }
    //     client.publish({
    //         destination: "/app/general/register",
    //         body: JSON.stringify(messageBody)
    //     })
    // }, 3000)

</script>

<style scoped lang="sass">
    @import "../static/style/main"

    $chat-width: 1000px

    #basic-chat
        width: $chat-width
        border-radius: 5px

    #chat-list
        background-color: antiquewhite
        height: 700px
        padding: 30px
        box-sizing: border-box
        overflow-y: auto
        float: left
        width: 70%

        #chat-list-bottom
            margin: 0

    .chat-members-bar
        width: 30%

    #chat-control-panel
        background-color: cornflowerblue
        height: 100px
        position: relative
        clear: both

        .centralized
            top: 50%
            transform: translate(0, -50%)
            position: absolute

        $text-area-width: $chat-width - $chat-width * 20/100
        $text-area-margin-left: 20px

        textarea
            margin-left: $text-area-margin-left
            display: block
            width: $text-area-width
            top: 50%
            transform: translate(0, -50%)
            position: absolute
            float: left
            resize: none
            border-radius: 5px
            padding: 3px 5px

        #send-message-button
            margin-left: $text-area-width + $text-area-margin-left + 20px
</style>