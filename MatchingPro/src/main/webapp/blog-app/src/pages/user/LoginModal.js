import React, {  useEffect, useState} from 'react';
import {  Modal, Button } from 'react-bootstrap';

function LoginModal(props) {
const setToken = props.setToken;
  console.log(6,setToken)
  
  const isToken = props.data;
  const setIsToken = props.data2;

  const [user, setUser] = useState({
    loginid: "",
    password: "",
  });
  
  // const [isToken, setIsToken] = useState(false);  
  // 토큰 있는 지 없는 지 확인용 state. 0면 없고  1면 있다 

  const inputHandle = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value,
    });
  };

  const loginRequest = () => {
    let person = {
      loginid : user.loginid,
      password: user.password
    }

    fetch("http://localhost:8000/login", {
      method: "POST",
      body: JSON.stringify(person),
      headers: {
        'Content-Type': "application/json; charset=utf-8"
      }
    }).then(res => {
      //console.log("첫 번째 then의 res", res);
      for (let header of res.headers.entries()) {


        if (header[0] === "authorization")

                
        
        {
          let data = header[1];
          data = data.substring(7);
          console.log(data);
          localStorage.setItem("Authorization", data);

          console.log(props.location);
          
          setToken();

          //setIsToken(true);
          //console.log(isToken);
          //if(isToken){
          //  console.log("토큰 생성");
          //}else{
          //  console.log("토큰 생성 안됨");
          //}


        }
      }
      return res.text();
    }).then(res => {
      //console.log("두 번째 then의 res", res);
      alert(res);   // 로그인의 결과

      //location.isToken(true);
      //console.log(isToken);
    });
  }

  useEffect(() => {
  }, []);




  return (



    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">
          Modal heading
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <h4>Centered Modal</h4>
        <p>
          Cras mattis consectetur purus sit amet fermentum. Cras justo odio,
          dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac
          consectetur ac, vestibulum at eros.
        </p>
      </Modal.Body>
      <Modal.Footer>
        <Button onClick={props.onHide}>Close</Button>
      </Modal.Footer>
    </Modal>
  );
}

function LoginModalhideshow() {
  const [modalShow, setModalShow] = React.useState(false);

  return (
    <>
      <Button variant="primary" onClick={() => setModalShow(true)}>
        Launch vertically centered modal
      </Button>

      <LoginModal
        show={modalShow}
        onHide={() => setModalShow(false)}
      />
    </>
  );
}; 

export default LoginModal;