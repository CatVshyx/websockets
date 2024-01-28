const stompClient = new StompJs.Client({
  brokerURL: "ws://localhost:8080/gs-guide-websocket",
});

stompClient.onConnect = (frame) => {
  setConnected(true);
  console.log("Connected: " + frame);
  stompClient.subscribe("/topic/greetings", (greeting) => {
    showGreeting(JSON.parse(greeting.body));
  });
};

stompClient.onWebSocketError = (error) => {
  console.error("Error with websocket", error);
};

stompClient.onStompError = (frame) => {
  console.error("Broker reported error: " + frame.headers["message"]);
  console.error("Additional details: " + frame.body);
};

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
  } else {
    $("#conversation").hide();
  }
  $("#greetings").html("");
}

function connect() {
  stompClient.activate();
}

function disconnect() {
  stompClient.deactivate();
  setConnected(false);
  console.log("Disconnected");
}

function sendMessage() {
  stompClient.publish({
    destination: "/app/hello",
    body: JSON.stringify({
      name: $("#name").val(),
      message: $("#message").val(),
    }),
  });
}

function showGreeting(message) {
  $("#greetings").append(
    "<h6> From:" + message.name + "</h6><p>" + message.message + "</p>"
  );
  // $("#greetings").append(
  //   "<tr><td> From:" +
  //     message.name +
  //     "</td><td> Content:" +
  //     message.message +
  //     "</td></tr>"
  // );
}
function resetMessage() {
  const message = document.getElementById("message");

  if (message.value != "") {
    message.value = "";
  }
}
$(function () {
  $("form").on("submit", (e) => e.preventDefault());
  $("#connect").click(() => connect());
  $("#disconnect").click(() => disconnect());
  $("#send").click(() => {
    sendMessage();
    resetMessage();
  });
});
