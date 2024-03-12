export function noEmptyFileds(obj: { [key: string]: any }): boolean {
  let result = true;
  Object.values(obj).forEach((v) => {
    if (v == '') result = false;
  });
  return result;
}
