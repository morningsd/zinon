import React, {Component} from 'react';
import { Form, FormControl, InputGroup, Button, Container, Row, Col } from "react-bootstrap";
import Header from "./Header";
import '../App.css';

class Register extends Component {

    render() {
        return (
	    <>
		<header>
	            <Header />
		</header>
		<Container fluid className="h-100 register-page">
		    <div className="d-flex justify-content-center align-items-center login-form-wrapper">
			<Form className="w-35 register-form">
			    <h1>Register</h1>
			    <Form.Group className="mb-2" controlId="formBasicEmail">
				<Form.Label>Email address</Form.Label>
				<Form.Control type="email" placeholder="Enter email" />
				<Form.Text className="text-muted">
				    We'll never share your email with anyone else.
				</Form.Text>
			    </Form.Group>
			    
			    <Form.Group className="mb-2" controlId="formBasicUsername">
				<Form.Label>Username</Form.Label>
				<InputGroup className="mb-2">
				    <InputGroup.Text>@</InputGroup.Text>
				    <FormControl placeholder="Username" />
				</InputGroup>
			    </Form.Group>

			    <Row className="align-items-center">
				<Col>
				    <Form.Label>First name</Form.Label>
				    <Form.Control className="mb-2" placeholder="First name" />
				</Col>
				<Col>
				    <Form.Label>Last name</Form.Label>
				    <FormControl className="mb-2" placeholder="Last name" />
				</Col>
			    </Row>
			    
			    <Form.Group className="mb-2" controlId="formBasicPassword">
				<Form.Label>Password</Form.Label>
				<Form.Control type="password" placeholder="Password" />
			    </Form.Group>
			    <Button variant="primary" type="submit">
				Submit
			    </Button>
			</Form>
		    </div>
		</Container>
	    </>
        );
    }

}

export default Register
