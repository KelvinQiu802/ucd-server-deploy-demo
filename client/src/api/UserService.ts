import HOST from './HOST';

export async function getAllUsers(jwt: string) {
  const response = await fetch(`${HOST}/users`, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${jwt}`,
    },
  });
  const result = await response.json();
  return JSON.stringify(result, null, 2);
}
