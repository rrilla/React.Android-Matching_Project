import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Carousel, Jumbotron, Button, Breadcrumb, Card, ListGroup, ListGroupItem, ResponsiveEmbed, Form, FormControl, Modal } from 'react-bootstrap';
import styled from 'styled-components';

const SlideStyle = styled.div`
	margin-top:15%;
	margin-bottom:4%;
`;

const MainCardStyle = styled.div`
  width: 100%;
  margin: auto;
`;

const Team_detail = (props) => {
	console.log("Team_detail:: 해당 page의 팀 ID: ", props.match.params.id);
	const teamId = props.match.params.id;
	const [team, setTeam] = useState([]);
	const { id, explaintation, name } = team;
	const [owner, setOwner] = useState([]);
	const [users, setUsers] = useState([]);

	const [users2, setUsers2] = useState([]);

	const [rteam, setRteam] = useState([]);

	const rteamplus = () => {
		alert("clickd");
		setRteam({
			...rteam,
			id: 1,
			nickname: "a"
		})
	}
	useEffect(() => {
		fetch(`http://localhost:8000/teamDetail/${teamId}`, {
			method: "get",
		}).then((res) => {
			console.log("aaa",res);
			return res.json()
			})
			.then((res) => {
				console.log("Team_detail:: fetch 받아온 team의 response", res);
				setTeam(res);
				setOwner(res.owner);
				setUsers(res.users);
			});
	}, []);

	const joinTeamReq = () => {
		fetch(`http://localhost:8000/user/apply1/${teamId}`, {
			method: "post",
			headers: {
				'Content-Type': "application/json; charset=utf-8",
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => res.text())
			.then(res => {
				if (res === "ok") alert("팀가입 요청 완료");
				else alert("팀가입 요청 실패");
			});
	};

	const [show, setShow] = useState(false);

	const handleClose = () => setShow(false);
	const handleShow = () => {

		fetch("http://localhost:8000/user/myTeam", {
			method: "get",
			headers: {
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => {
			console.log("신청하는 입장:: login한 ID의 Team Id display res", res);
			return res.text();
		}).then((res) => {
			console.log("신청하는 입장:: login한 ID의 Team Id display", res);

			fetch(`http://localhost:8000/teamDetail/${res}`, {
				// 여기 들어가는 res는 현재 로그인 한 ID의 TeamID // 팀 정보 가져와서 소속 선수, 팀장 display
				method: "get",
			}).then((res) => {
				return res.json();
			}).then((res) => {
				console.log("신청하는 팀 정보:: Team info fetch display res: ", res);
				setUsers2(res.users);
			});
		});
		setShow(true)
	};

	const z = () => {
		alert("click");

	}
	const sss = () => {
		let teamInfo = {
			/* loginid: user.loginid,
			password: user.password */
			//user1: {id: 1},
			user2: { id: 1 },
			user3: { id: 1 },
			user4: { id: 1 },
			user5: { id: 1 },
			user6: { id: 1 },
			user7: { id: 1 },
			user8: { id: 1 },
			user9: { id: 1 },
			user10: { id: 1 },
			user11: { id: 1 },

		}

		fetch(`http://localhost:8000/user/teamInfo`, {
			method: "post",
			body: JSON.stringify(teamInfo),
			headers: {
				'Content-Type': "application/json; charset=utf-8",
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => res.text())
			.then(res => {
				if (res === "ok") alert("tema info create");
				else alert("팀가입 요청 실패");
			});
	};

	const aaa = () => {
		let battle = {

		}
		fetch(`http://localhost:8000/user/matchApply/${teamId}`, {
			method: "post",
			body: JSON.stringify(battle),
			headers: {
				'Content-Type': "application/json; charset=utf-8",
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => {
			console.log("battle 신청 res : ", res);
			return res.text()
		})
			.then(res => {
				if (res === "ok") alert("팀가입 요청 완료");
				else alert("팀가입 요청 실패");
			});
	};

	return (
		<Container>
			<Modal show={show} size={"lg"} onHide={handleClose}>
				<Modal.Header closeButton>
					<Modal.Title>대전신청</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					 {users2.map((res) => (//이 팀에 들어온 파티 번호 : {res.id}
						<Col md={3}>🏃 {res.nickname}
							<Button onClick={rteamplus} size="sm" variant="outline-success">신청d하기</Button></Col>

					))} 


					<Form>  {/* , 'radio' */}
						{['radio'].map((type) => (
							<div key={`custom-inline-${type}`} className="mb-3">
								{users2.map((res) => (//이 팀에 들어온 파티 번호 : {res.id}
									<div>
										<Form.Check
										custom
										inline
										label={res.nickname}
										type={type}
										id={`custom-inline-${type}-${1}`}
										/>
									</div>
								))}

								{/* <Form.Check
									custom
									inline
									label="2"
									type={type}
									id={`custom-inline-${type}-2`}
								/>
								<Form.Check
									custom
									inline
									disabled
									label="3 (disabled)"
									type={type}
									id={`custom-inline-${type}-3`}
								/> */}
							</div>
						))}
					</Form>

<Form>
  <Form.Group controlId="exampleForm.SelectCustomHtmlSize">
    <Form.Label>Select with three visible options</Form.Label>
    <Form.Control as="select" htmlSize={3} custom>
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
    </Form.Control>
  </Form.Group>
</Form>

<Form>
  {['checkbox', 'radio'].map((type) => (
    <div key={`custom-${type}`} className="mb-3">
      <Form.Check 
        custom
        type={type}
        id={`custom-${type}`}
        label={`Check this custom ${type}`}
      />

      <Form.Check
        custom
        disabled
        type={type}
        label={`disabled ${type}`}
        id={`disabled-custom-${type}`}
      />
    </div>
  ))}
</Form>

					<Container>
						<br /><br /><br /><br /><br />

						<Button onClick={aaa} variant="outline-success">신청하기</Button>

					</Container>
				</Modal.Body>
				<Modal.Footer>
					<Button variant="secondary" onClick={handleClose}>
						Close
					</Button>

					{/*rteam.map((res) => (//이 팀에 들어온 파티 번호 : {res.id}
						<Col md={3}>🏃 {res.nickname}
						</Col>
						
					))*/}
				</Modal.Footer>
			</Modal>
			<SlideStyle>
				<MainCardStyle>
					<Jumbotron>
						<Row>
							<Col md={12}><h1>⚽ {name}</h1></Col>
							<Col md={12}><hr /></Col>
							<Col md={3}><h5>👑 {owner.nickname}</h5></Col>
							<Col md={8}><h5>📄 {explaintation}</h5></Col>
							<Col md={12}><hr /></Col>
							<Col md={3}><h3>🏃‍♀️ Member</h3></Col>
							<Col md={8}></Col>
							<Col md={12}><br /></Col>
							{users.map((res) => (//이 팀에 들어온 파티 번호 : {res.id}
								<Col md={3}>🏃 {res.nickname}</Col>
							))}
							<Col md={12}><hr /></Col>
							<Col md={3}>
								<Button onClick={joinTeamReq} variant="outline-success">가입신청</Button>
							</Col>
							<Col md={3}>
								<Button onClick={handleShow} variant="outline-success">대전신청</Button>
							</Col>
							
						</Row>
					</Jumbotron>
				</MainCardStyle>
			</SlideStyle>
		</Container>
	);
};

export default Team_detail;