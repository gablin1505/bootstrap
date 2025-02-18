async function addUserWithFormHandle() {
  const form = document.getElementById("addUser");

  form.addEventListener("submit", async (event) => {
    const name = form.querySelector("#name").value.trim();
    const lastname = form.querySelector("#lastName").value.trim();
    const age = form.querySelector("#age").value.trim();
    const email = form.querySelector("#email").value.trim();
    const password = form.querySelector("#password").value.trim();

    const response = await createUserAction({
      name,
      lastname,
      age,
      email,
      password,
    });

    form.reset();
  });
}
