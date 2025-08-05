const axios = require("axios");

async function signIn() {
  const response = await axios.post(
    "http://localhost:9099/identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=fake-api-key",
    {
      email: "testuser12@gmail.com",
      password: "test1278",
      returnSecureToken: true,
    }
  );

  console.log("ID Token:", response.data.idToken);
}

signIn().catch(console.error);
