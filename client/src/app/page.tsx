'use client';
import { login, register } from '@/api/AuthService';
import { getAllUsers } from '@/api/UserService';
import { noEmptyFileds } from '@/utils/Validator';
import { Button } from 'primereact/button';
import { InputText } from 'primereact/inputtext';
import { InputTextarea } from 'primereact/inputtextarea';
import { ChangeEvent, useState } from 'react';
import styles from './page.module.css';

export default function Home() {
  const [registerInfo, setRegisterInfo] = useState<RegisterInfo>({
    userName: '',
    email: '',
    password: '',
  });

  const [loginInfo, setLoginInfo] = useState<LoginInfo>({
    userName: '',
    password: '',
  });
  const [jwt, setJwt] = useState('');
  const [registerResult, setRegisterResult] = useState('No Response');
  const [loginResult, setLoginResult] = useState('No Response');
  const [allUsers, setAllUsers] = useState('No Response');

  const updateRegisterInfo = (e: ChangeEvent<HTMLInputElement>) => {
    setRegisterInfo((prev) => {
      return { ...prev, [e.target.name]: e.target.value };
    });
  };

  const updateLoginInfo = (e: ChangeEvent<HTMLInputElement>) => {
    setLoginInfo((prev) => {
      return { ...prev, [e.target.name]: e.target.value };
    });
  };

  const handleRegister = async () => {
    if (!noEmptyFileds(registerInfo)) {
      setRegisterResult('All Fields Must Have a Value');
      return;
    }
    const result = await register(registerInfo);
    setRegisterResult(result);
  };

  const handleLogin = async () => {
    if (!noEmptyFileds(loginInfo)) {
      setLoginResult('All Fields Must Have a Value');
      return;
    }
    const result = await login(loginInfo);
    setLoginResult(result);
  };

  const handleFetch = async () => {
    if (jwt == '') {
      setAllUsers('All Fields Must Have a Value');
      return;
    }
    const result = await getAllUsers(jwt);
    setAllUsers(result);
  };

  return (
    <main className={styles.main}>
      <h1>Group 2 Deployment Demo</h1>
      <div className={styles.tips}>
        <div>{'1. Register -> Login -> Access API with JWT'}</div>
        <div>2. JWT expires in 60 seconds.</div>
      </div>
      <h1>Register</h1>
      <div className={styles.form}>
        <div className={styles.inputLine}>
          <div>User Name: </div>
          <InputText
            className='p-inputtext-sm'
            name='userName'
            onChange={updateRegisterInfo}
          />
        </div>
        <div className={styles.inputLine}>
          <div>E-mail: </div>
          <InputText
            className='p-inputtext-sm'
            name='email'
            onChange={updateRegisterInfo}
          />
        </div>
        <div className={styles.inputLine}>
          <div>Password: </div>
          <InputText
            className='p-inputtext-sm'
            type='password'
            name='password'
            onChange={updateRegisterInfo}
          />
        </div>
        <Button label='Submit' outlined size='small' onClick={handleRegister} />
        <InputTextarea value={registerResult} cols={60} rows={5} />
      </div>
      <h1>Login</h1>
      <div className={styles.form}>
        <div className={styles.inputLine}>
          <div>User Name: </div>
          <InputText
            className='p-inputtext-sm'
            name='userName'
            onChange={updateLoginInfo}
          />
        </div>
        <div className={styles.inputLine}>
          <div>Password: </div>
          <InputText
            className='p-inputtext-sm'
            type='password'
            name='password'
            onChange={updateLoginInfo}
          />
        </div>
        <Button label='Login' outlined size='small' onClick={handleLogin} />
        <InputTextarea value={loginResult} cols={60} rows={5} />
      </div>
      <h1>Get All Users</h1>
      <div className={styles.form}>
        <div className={styles.inputLine}>
          <div>JSON Web Token: </div>
          <InputText
            className='p-inputtext-sm'
            onChange={(e) => setJwt(e.target.value)}
          />
        </div>
        <Button label='Fetch' outlined size='small' onClick={handleFetch} />
        <InputTextarea value={allUsers} cols={60} rows={5} />
      </div>
    </main>
  );
}
