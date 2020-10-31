import React, { useState, useEffect } from 'react';
import { Button, Container } from 'react-bootstrap';

const MyTeam = () => {
	const joinTeamReq = () => {
		console.log(partys);
	};

	const [team, setTeam] = useState([]);
	const { id, explaintation, name } = team;
	const [partys, setPartys] = useState([]);
	const [user, setUser] = useState([]);

	useEffect(() => {
		// 해당 페이지에 진입했을 때 한 번만 실행
		fetch("http://localhost:8000/user/myTeam", {
			method: "get",
			headers: {
				'Authorization': localStorage.getItem("Authorization")
			}// 어th오라이제이션
		}).then((res) => {
			console.log("asdasdsadsad");
			console.log(res);
			return res.text();
		}) // id가 받아와져야 정상
			.then((res) => {
				console.log("현재 로그인 되어있는 id의 pk : ");
				console.log(res);	// res : id
				fetch(`http://localhost:8000/teamDetail/${res}`, {
					method: "get",
				}) // 위 주소에서 데이터를 받아와서
					.then((res) => {
						return res.json();
					}) // 받아온 데이터를 json type으로 바꿔서
					.then((res) => {
						setTeam(res);
						console.log(res);
						console.log("team detail ===============");
						console.log(res.owner.id);

						//console.log(res.party[0].id);
						setUser(res.owner);
						setPartys(res.partys);
					}); // state 변수에 넣어준다
			}); // state 변수에 넣어준다
	}, []);


	return (
		<Container>
			팀 이름 : {name}
			<br />
			팀 이름 : {id}
			<br />
			팀 이름 : {explaintation}
			<br />

			<br /><br />
			팀장번호: {user.id}
			<br />
			팀장닉네임: {user.nickname}
			<br />
			<Button onClick={joinTeamReq} variant="outline-success">가입신청</Button>
			<br />
			{partys.map((res) => (
				<div>이 팀에 들어온 파티 번호 : {res.id}</div>
			))}

		</Container>
	);
};
// 일단 우리팀의 id를 찾는다
// fetch로 팀 상세보기해서 우리팀 정보 가져와서 보여준다
//팀장일시 : 팀 가입 요청  목록 ) partys로 가져움 + 승인 버튼 , 추방 버튼 

// localhost:8000/givemeid 요청을 보내면 그 유저가 어떤 유저인지 파악해서 id를 리턴해주는 걸 만들면 되겠지?


export default MyTeam;