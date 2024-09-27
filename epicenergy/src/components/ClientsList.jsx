import { useEffect } from "react";
import { useState } from "react";

const ClientsList = ({ clienti }) => {
  const [info, setInfo] = useState([]);
  const fillClient = async (clienti) => {
    try {
      let response = await fetch("http://localhost3000/clienti");
      if (response.ok) {
        let { data } = await response.json;
        let arrayClienti = [];
        for (let index = 0; index < 5; index++) {
          arrayClienti.push(data[index]);
        }
        setInfo(arrayClienti);
      } else {
        throw new Error("Error in fetching clients");
      }
    } catch (err) {
      console.log("Error", err);
    }
  };

  useEffect(() => {
    fillClient(clienti);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);
  return <>{info && info.map((cliente) => <CardCliente info={cliente} key={cliente.id} />)}</>;
};
export default ClientsList;
