import HOST from './HOST';

export async function register(registerInfo: RegisterInfo): Promise<string> {
  const response = await fetch(`${HOST}/auth/register`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(registerInfo),
  });
  if (response.status == 201) {
    return JSON.stringify({ status: 201, message: 'Register Success' });
  }
  const result = await response.json();
  console.log(result);
  return JSON.stringify(result, null, 2);
}

export async function login(loginInfo: LoginInfo): Promise<string> {
  const response = await fetch(`${HOST}/auth/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(loginInfo),
  });
  const result = await response.json();
  return JSON.stringify(result, null, 2);
}
