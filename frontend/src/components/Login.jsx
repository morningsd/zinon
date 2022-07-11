import React, {Component} from 'react';
import { Form, Button, Container } from "react-bootstrap";
import Header from "./Header";
import '../App.css';

class Login extends Component {

    render() {
        return (
	    <>
		<header>
	            <Header />
		</header>
		<Container fluid className="login-page">
		    <div className="d-flex justify-content-center align-items-center login-form-wrapper">
			<Form className="w-25 login-form">
			    <h1>Login</h1>
			    <Form.Group className="mb-3" controlId="formBasicEmail">
				<Form.Label>Email address</Form.Label>
				<Form.Control type="email" placeholder="Enter email" />
				<Form.Text className="text-muted">
				    We'll never share your email with anyone else.
				</Form.Text>
			    </Form.Group>

			    <Form.Group className="mb-3" controlId="formBasicPassword">
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

export default Login
