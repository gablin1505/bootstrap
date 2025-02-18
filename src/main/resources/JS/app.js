// Обработчик на запуск всех хендлеров фронт стороны после загрузки страницы

document.addEventListener("DOMContentLoaded", async () => {
  await userInfoInNavbarHandle();
  await fillTableHandle();
  await addUserWithFormHandle();
});

async function userInfoInNavbarHandle() {
  const navbar = document.getElementById("userEmail");

  const user = await getUserAction();

  navbar.innerHTML = `
    <strong>${user.email}</strong>
    with roles:
    ${currentUser.role.map((role) => role.roleName).join(" ")}`;
}
