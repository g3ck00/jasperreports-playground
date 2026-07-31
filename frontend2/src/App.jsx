import { useState } from "react";
import ComprobanteTable from "./components/ComprobanteTable";
import { getComprobantes } from "./services/comprobanteService";


function App() {


  const [comprobantes, setComprobantes] = useState([]);


  const cargarComprobantes = async () => {

    const response =
        await getComprobantes();

    setComprobantes(
        response.data.content ?? response.data
    );
  };


  return (

      <div>

        <h1>The JasperReports Playground</h1>
          <h6>It varks!</h6>


        <button onClick={cargarComprobantes}>
          Mostrar comprobantes
        </button>

        <hr/>

        <ComprobanteTable
            comprobantes={comprobantes}
        />

      </div>

  );

}


export default App;