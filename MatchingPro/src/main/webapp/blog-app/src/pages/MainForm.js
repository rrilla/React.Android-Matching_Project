import React, { useEffect, useState } from 'react';
import Image from 'react-bootstrap/Image'
import styled from 'styled-components';
import { Container, Row, Col, Carousel, Jumbotron, Button, Breadcrumb, Card, ListGroup, ListGroupItem, ResponsiveEmbed, Form, FormControl, Modal, Tabs, Tab } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import TeamCard from '../components/TeamCard';
import UserCard from '../components/UserCard';
import Background from '../components/Background';
import Slide from '../components/Slide';
import LoginForm from './user/LoginForm';
import LoginModal from '../components/LoginModal';

const MainCardStyle = styled.div`
    width: 100%;
    margin: auto;
  `;



const LinkStyle = styled.span`
    color : black;
  `;

const MainForm = () => {

  useEffect(() => {
    // mainForm Teamcard data 
    fetch("http://localhost:8000/teamList", {
      method: "get",
    }).then((res) => {
      console.log("mainForm:: teamList rsponse", res);
      return res.json();
    }).then((res) => {
      console.log("mainForm:: teamList -> json data", res);
      setTeams(res);
    });


    fetch("http://localhost:8000/userList", {
      method: "get",
    }).then((res) => {
      console.log("mainForm:: userList rsponse", res);
      return res.json();
    }).then((res) => {
      console.log("mainForm:: userList -> json data", res);
      setTusers(res);
    });

    fetch("http://localhost:8000/rank", {
      method: "get",
    }).then((res) => {
      console.log("mainForm:: rank rsponse", res);
      return res.json();
    }).then((res) => {
      console.log("mainForm:: rank -> json data", res);
      setRank(res);
    });

  }, []);


  const [rank, setRank] = useState([]);
  const [teams, setTeams] = useState([]);
  const [users, setTusers] = useState([]);
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  return (

    <Container>
      <Slide />
      <Row>
        <MainCardStyle>
          <Jumbotron>
            <h1>아마추어 축구 여기서 시작하세요!</h1>
            <Button variant="info">

              <Link to="/Team_create"><LinkStyle>팀 만들기</LinkStyle></Link>
            </Button>
            <br />
            <br />

            <h3> 팀 선수 목록을 검색해 보세요</h3>
            <Tabs defaultActiveKey="rank" id="uncontrolled-tab-example">
              <Tab eventKey="team" title="팀목록보기">

                <Row>
                  {teams.map((res) => (<Col md={4}><TeamCard team={res} key={res.id}></TeamCard></Col>))}
                </Row>


              </Tab>
              <Tab eventKey="user" title="선수목록보기">
                <Row>
                  {users.map((res) => (<Col md={4}><UserCard user={res} key={res.id}></UserCard></Col>))}
                </Row>



              </Tab>

              <Tab eventKey="rank" title="Rank">

                <Row>
                  <Col md={3}>TEAM</Col>
                  <Col md={2}>SCORE</Col>
                  <Col md={2}>W</Col>
                  <Col md={2}>L</Col>
                  <Col md={2}>D</Col>
                </Row>
                {rank.map((res) => <Row>
                  <Col md={3}>{res.team.name}</Col>
                  <Col md={2}>{res.total}</Col>
                  <Col md={2}>{res.win}</Col>
                  <Col md={2}>{res.lose}</Col>
                  <Col md={2}>{res.draw}</Col>
                </Row>)}




              </Tab>
            </Tabs>
            {/* <Form inline>
                <FormControl type="text" placeholder="Search" className="mr-sm-2" />
                <Button variant="outline-success">Search</Button>
              </Form> */}
          </Jumbotron>
        </MainCardStyle>
      </Row>





    </Container>
  );
};

export default MainForm;