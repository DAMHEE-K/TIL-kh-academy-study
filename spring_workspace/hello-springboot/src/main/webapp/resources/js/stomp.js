console.log('Hello stomp.js');

// const ws = new WebSoket(`ws://${location.host}/spring/stomp`);
// SockJS 는 WebSocket 프로토콜을 지원하지 않는 브라우저에서도 사용 가능함

const ws = new SockJS(`http://${location.host}/spring/stomp`); // endpoint
const stompClient = Stomp.over(ws);

stompClient.connect({}, (frame) => {
	console.log('open : ', frame);
	
	// 구독신청 
	stompClient.subscribe('/app/notice', (message) => {
		console.log('/app/notice : ', message);
		renderMessage(message);
	});
	stompClient.subscribe(`/app/notice/${memberId}`, (message) => {
		console.log(`/app/notice/${memberId} : `, message);
		renderMessage(message);
	});
	
});

const renderMessage = (message) => {
	const {type, from, to, content, createdAt} = JSON.parse(message.body);
	console.log(type, from, to, content, createdAt);
	
	const $noticeModal = $("#noticeModal");
	$noticeModal.find(".modal-title").html(`<span class='badge badge-primary from'>${to}</span>`);
	$noticeModal.find(".modal-body").html(content);
	$noticeModal.find(".modal-footer .from").html(from); 
	$noticeModal.find(".modal-footer .when").html(createdAt); 
	$noticeModal.modal();
}


