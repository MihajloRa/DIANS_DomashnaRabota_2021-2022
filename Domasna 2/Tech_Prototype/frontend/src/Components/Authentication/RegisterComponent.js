import React from 'react';
import authenticationService from '../../Service/authenticationService';
import { useForm } from 'react-hook-form';
import { Link, useHistory } from 'react-router-dom';
import { Form, FormControl, FormGroup, FormLabel, Button} from 'react-bootstrap'


const RegisterComponent = () => {
    const {register, handleSubmit} = useForm();
    let history = useHistory();

    const onSubmit = (data) => {
        authenticationService.registerUser(data)
        .then(() => {
            history.push("/login");
        }).catch((error) => {
            console.error(error);
        });
    }

    return (
        <Form onSubmit={handleSubmit(onSubmit)}>
            <FormGroup className="mb-3">
                <FormLabel>Username</FormLabel>
                <FormControl {...register("username")}type="text" placeholder="Enter your Username" />
            </FormGroup>
            <FormGroup className="mb-3">
                <FormLabel>Password</FormLabel>
                <FormControl {...register("password")} type="password" placeholder="Enter your Password" />
            </FormGroup>
            <FormGroup className="mb-3">
                <FormLabel>Repeat Password</FormLabel>
                <FormControl {...register("repeatedPassword")} type="password" placeholder="Enter your Password" />
            </FormGroup>
            <FormGroup className="mb-3">
                <FormLabel>Email</FormLabel>
                <FormControl {...register("email")} type="email" placeholder="Enter your Password" />
            </FormGroup>
            <FormGroup className="mb-3">
                <Button type="submit">Register</Button>
                <Link to='/login'>
                    Already have an account?
                </Link>
            </FormGroup>
        </Form>
    );
}

export default RegisterComponent;