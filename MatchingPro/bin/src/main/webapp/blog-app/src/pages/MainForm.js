import React, { useEffect, useState } from 'react';
import Image from 'react-bootstrap/Image'
import styled from 'styled-components';
import { Container, Row, Col, Carousel, Jumbotron, Button, Breadcrumb, Card, ListGroup, ListGroupItem, ResponsiveEmbed, Form, FormControl, Modal } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import TeamCard from '../components/TeamCard';
import UserCard from '../components/UserCard';
import Background from '../components/Background';
import Slide from '../components/Slide';

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


    fetch("http://localhost:8000//userList", {
      method: "get",
    }).then((res) => {
      console.log("mainForm:: userList rsponse", res);
      return res.json();
    }).then((res) => {
      console.log("mainForm:: userList -> json data", res);
      setTusers(res);
    });
  }, []);

  const [teams, setTeams] = useState([]);
  const [users, setTusers] = useState([]);

  return (

    <Container>
      <Slide />
      <Row>
        <MainCardStyle>
          <Jumbotron>
            <h1>Hello, world!</h1>
            <p>지역별로, 날짜별로 경기를 검색해 보세요!</p>
            <p><Button variant="primary">전체 경기 목록 보기 </Button></p>
            <Link to="/Team_create"><LinkStyle>팀 만들기</LinkStyle></Link>
            <Form inline>
              <FormControl type="text" placeholder="Search" className="mr-sm-2" />
              <Button variant="outline-success">Search</Button>
            </Form>
          </Jumbotron>
        </MainCardStyle>
      </Row>

      <Row>
        {teams.map((res) => (<Col md={4}><TeamCard team={res} key={res.id}></TeamCard></Col>))}
      </Row>

      <Row>
        {/* {users.map((res) => (<Col md={4}><UserCard team={res} key={res.id}></UserCard></Col>))} */}

      </Row>

    </Container>
  );
};

export default MainForm;