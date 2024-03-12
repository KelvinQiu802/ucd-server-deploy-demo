'use client';
import { Button } from 'primereact/button';
import { InputText } from 'primereact/inputtext';
import { ChangeEvent, useState } from 'react';
import styles from './page.module.css';

interface RegisterInfo {
  userName: string;
  email: string;
  password: string;
}

interface LoginInfo {
  userName: string;
  password: string;
}

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

  const handleRegister = () => {};

  const handleLogin = () => {};

  const handleFetch = () => {};

  return (
    <main className={styles.main}>
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
      </div>
    </main>
  );
}
