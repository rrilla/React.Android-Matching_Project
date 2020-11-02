import React, { useState, useEffect } from 'react';
import { Container, Jumbotron, Button, Form, FormControl, Row, Col } from 'react-bootstrap';
import Party from '../../components/Party';
import Background from '../../components/Background';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const MainCardStyle = styled.div`
  width: 100%;
  margin: auto;
`;

const LinkStyle = styled.span`
  color : black;
`;

const SlideStyle = styled.div`
	margin-top:15%;
	margin-bottom:4%;
`;

const MyTeam = () => {
	const joinTeamReq = () => {
		alert("ddd");
	};

	const [team, setTeam] = useState([]);
	const { id, explaintation, name } = team;
	const [owner, setOwner] = useState([]);
	const [partys, setPartys] = useState([]);
	const [users, setUsers] = useState([]);

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
						console.log("MyTeam::  fetch res: ", res);
						setTeam(res);
						setOwner(res.owner);
						setUsers(res.users)
						setPartys(res.partys);
						/* 						(res.partys.id).map((res) =>{
													console.log("MyTeam:: first fetch-party", res);
												}) */
						console.log("MyTeam:: first fetch-party", res.partys.id);
					}); // state 변수에 넣어준다
			}); // state 변수에 넣어준다
	}, []);


	return (
		<Container>
			<SlideStyle>
				<MainCardStyle>
					<Jumbotron>
						<Row>
							<Col md={4}><h1>⚽ {name}</h1></Col>
							<Col md={8}></Col>
							<Col md={12}><hr /></Col>
							<Col md={4}><h5>👑 {owner.nickname}</h5></Col>
							<Col md={8}><h5>📄 {explaintation}</h5></Col>
							<Col md={12}><hr /></Col>
							<Col md={4}><h3>🏃‍♀️ Member</h3></Col>
							<Col md={8}></Col>
							<Col md={12}><br /></Col>
							{users.map((res) => (//이 팀에 들어온 파티 번호 : {res.id}
								<Col md={3}>🏃 {res.nickname}</Col>
							))}
							<Col md={12}><hr /></Col>
							<Col md={4}><h3>🙌 Apply</h3></Col>
							<Col md={8}><h3>{partys.length}건</h3></Col>
							{/* <Col md={6}><Button onClick={joinTeamReq}>전체수락</Button></Col> */}
							<Col md={12}><br /></Col>
							{partys.map((res) => (//이 팀에 들어온 파티 번호 : {res.id}
								<Col md={4}>
									🏃 {res.id}&nbsp;&nbsp;&nbsp;
									<Button onClick={joinTeamReq}>수락</Button>
								</Col>
							))}
							<Col md={12}><hr /></Col>
							<Col md={4}><h3>⚔ Battle</h3></Col>
							<Col md={8}><h3>{partys.length}건</h3></Col>
							{partys.map((res) => (//이 팀에 들어온 파티 번호 : {res.id}
								<Col md={4}>
									💥 {res.id}&nbsp;&nbsp;&nbsp;
									<Button onClick={joinTeamReq}>수락</Button>
								</Col>
							))}
						</Row>
					</Jumbotron>
				</MainCardStyle>
			</SlideStyle>
			<Background></Background>
		</Container>
	);
};
// 일단 우리팀의 id를 찾는다
// fetch로 팀 상세보기해서 우리팀 정보 가져와서 보여준다
//팀장일시 : 팀 가입 요청  목록 ) partys로 가져움 + 승인 버튼 , 추방 버튼 
// localhost:8000/givemeid 요청을 보내면 그 유저가 어떤 유저인지 파악해서 id를 리턴해주는 걸 만들면 되겠지?
export default MyTeam;