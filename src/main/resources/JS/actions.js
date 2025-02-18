const createUserAction = async (user) => {
  const response = await fetch(apiPath.admin.index, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user),
  });
  return response;
};

const deleteUserAction = async (user) => {
  const response = await fetch(apiPath.admin.index, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user),
  });
  return response;
};

const updateUserAction = async (user) => {
  const response = await fetch(apiPath.admin.index, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user),
  });
  return response;
};

const getUsersAction = async () => {
  const response = await fetch(apiPath.admin.index, {
    method: "GET",
  });
  return response.json();
};

const getUserAction = async () => {
  const response = await fetch(apiPath.user.index);
  return response.json();
};
