import React, { useEffect, useState } from 'react';
import Image from 'react-bootstrap/Image'
import styled from 'styled-components';
import { Container, Row, Col, Carousel, Jumbotron, Button, Breadcrumb, Card, ListGroup, ListGroupItem, ResponsiveEmbed, Form, FormControl, Modal } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import TeamCard from '../components/TeamCard';

const MainForm = () => {
  const MainCardStyle = styled.div`
		width: 100%;
		margin: auto;
  `;

  const LinkStyle = styled.span`
    color : black;
  `;

  // style

  const [teams, setTeams] = useState([]);

  useEffect(() => {
    // 해당 페이지에 진입했을 때 한 번만 실행
    fetch("http://localhost:8000/teamList", {
      method: "get",
    }) // 위 주소에서 데이터를 받아와서
      .then((res) => {
        console.log(res);
        return res.json();
      }) // 받아온 데이터를 json type으로 바꿔서
      .then((res) => {
        console.log(res);
        setTeams(res);
      }); // state 변수에 넣어준다
  }, []);


  return (
    <Container>

      <Row>
        <Breadcrumb>
          <Breadcrumb.Item href="#">Home</Breadcrumb.Item>
          <Breadcrumb.Item href="#">Home</Breadcrumb.Item>
          <Breadcrumb.Item href="#">Home</Breadcrumb.Item>
        </Breadcrumb>
      </Row>

      <Row>
        <Col md={11}>
          <Carousel>

            <Carousel.Item>
              <img
                className="d-block w-100"
                src="1slideepic.png"
                alt="First slide"
              />
              <Carousel.Caption>
                <h3>First slide label</h3>
                <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
              </Carousel.Caption>
            </Carousel.Item>

            <Carousel.Item>
              <img
                className="d-block w-100"
                src="2slidecool.jpg"
                alt="Third slide"
              />
              <Carousel.Caption>
                <h3>Second slide label</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
              </Carousel.Caption>
            </Carousel.Item>

            <Carousel.Item>
              <img
                className="d-block w-100"
                src="3slideground.jpg"
                alt="Third slide"
              />
              <Carousel.Caption>
                <h3>Third slide label</h3>
                <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
              </Carousel.Caption>
            </Carousel.Item>

          </Carousel>
        </Col>
      </Row>
      <br />
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
        asd
        {teams.map((res) => (<Col md={4}><TeamCard team={res} key={res.id}></TeamCard></Col>))}
      </Row>
    </Container>
  );
};

export default MainForm;