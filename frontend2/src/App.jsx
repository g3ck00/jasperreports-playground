import {useEffect, useState} from 'react'


import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'

const API="http://localhost:8080/api"

function App() {
  const [comprobantes, setComprobantes] = useState([]);

  const [editandoId, setEditandoId] = useState(null);

  const [loading, setLoading] = useState(true);

  const [readUsuarios, setReadUsuarios]=useState(false);

  const [createUsuario, setCreateUsuario]=useState(false);

  const [mostrarFormularioCreateUsuario, setMostrarFormularioCreateUsuario]=useState(false);

  const [mostrarFormularioBuscarUsuario, setMostrarFormularioBuscarUsuario]=useState(false);

  const [errores, setErrores]=useState({});

  const [pagina, setPagina]=useState(0);
  const [totalPaginas, setTotalPaginas]=useState(0);

  const [mensajeExito, setMensajeExito]=useState("");

  const [idBusqueda,setIdBusqueda]=useState("");
  const [errorBusqueda, setErrorBusqueda]=useState("");

  const fetchComprobantes = async (page=0) => {
    const response=await fetch(`${API}/facturas?page=${page}&size=20`);
    const data=await response.json();

    try {
      setComprobantes(data.content);
      setPagina(data.number);
      setTotalPaginas(data.totalPages);

      console.log(data);

    } catch (error) {
      console.error("Error al obtener usuarios...");
    }
  }

  useEffect(() => {
    fetchComprobantes(pagina);
  }, [pagina])

  {/*
  useEffect(() => {
    fetch(`${API}/usuarios`)
        .then((res) => {
          if (!res.ok) throw new Error("Error al obtener usuarios...");
          return res.json();
        })
        .then((data) => {
          setUsuarios(data.content ?? data); // soporta Page o List
        })
        .catch((err) => console.log(err))
        .finally(() => setLoading(false));
  }, []);
  */}

  return (
      <div>
        <h1>The JasperReports Playground</h1>
        <h2>It varks!</h2>

        <div className={"container mt-4"}>
          <h2>Comprobantes</h2>

          <button disabled={pagina===0}
                  onClick={()=>setPagina(pagina-1)}
          >Anterior</button>

          <span>Pagina {pagina+1} de {totalPaginas}</span>

          <button disabled={pagina+1>=totalPaginas}
                  onClick={()=>setPagina(pagina+1)}
          >Siguiente</button>

          <br></br>
          <br></br>

          <div className={"rounded overflow-hidden"}>
            <table
                className={"table table-striped table-hover table-bordered table-dark rounded align-middle"}
                style={{tableLayout: "fixed", width: "100%"}}
            >
              <thead>
              <tr>
                <th>codigo</th>
                <th>codigo_documento</th>
                <th>codigo_documento_origen</th>
                {/*}
                <th>tipo_ambiente</th>
                <th>tipo_emision</th>
                {*/}
                <th>razon_social</th>
                <th>nombre_comercial</th>
                <th>ruc</th>
                <th>establecimiento</th>
                <th>punto_emision</th>
                <th>secuencial</th>
                <th>establecimiento_ce_origen</th>
                <th>secuencial_ce_origen</th>
                <th>direccion_matriz_emisor</th>
                <th>fecha_emision</th>
                <th>direccion_establecimiento</th>
                <th>contribuyente_especial</th>
                <th>obligado_contabilidad</th>
                <th>razon_social_comp_sr_trans</th>
                <th>moneda</th>
                <th>gr_fecha_ini_transporte</th>
                <th>gr_fecha_fin_transporte</th>
                <th>numero_autorizacion</th>
                <th>fecha_autorizacion</th>
                <th>clave_acceso</th>
                <th>correo_electronico1</th>
                <th>correo_electronico2</th>
                <th>contador_reenvio</th>
                <th>transaccion_origen</th>
                <th>estado_aprobacion</th>
                <th>fecha_aprobacion</th>
                <th>usuario_ingreso</th>
                <th>ubicacion_ingreso</th>
                <th>ace_cab_ce_codigo</th>
                <th>punto_emision_ce_origen</th>
                <th>fecha_emision_origen</th>
                <th>identificacion</th>
                <th>direccion</th>
                <th>total_sin_impuestos</th>
                <th>rise</th>
                <th>valor</th>
                <th>fe_guia_remision</th>
                <th>fe_total_descuento</th>
                <th>fe_propina</th>
                <th>fe_importe_total</th>
                <th>cr_periodo_fiscal</th>
                <th>gr_placa</th>
                <th>nc_motivo</th>
                <th>comprobante_estado</th>
                <th>estado</th>
                <th>usuario_aprobacion</th>
                <th>observacion_aprobacion</th>
                <th>fecha_estado</th>
                <th>observacion_estado</th>
                <th>fecha_ingreso</th>
                <th>usuario_modificacion</th>
                <th>fecha_modificacion</th>
                <th>ubicacion_modificacion</th>
                <th>age_licenc_codigo</th>
                <th>ace_cab_ce_age_licenc_codigo</th>
                <th>age_tip_id_codigo</th>
                <th>valor_retencion_iva</th>
                <th>valor_retencion_renta</th>
                <th>codigo_reembolso</th>
                <th>total_base_imponible_reembolso</th>
                <th>total_impuestos_reembolso</th>
                <th>total_comprobantes_reembolso</th>
              </tr>
              </thead>

              <tbody>
              {comprobantes.map((c)=>(
                  <tr key={c.codigo} style={{height: "50px"}}>
                    <td style={{ overflowWrap: "break-word" }}>{c.codigo_documento}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.codigo_documento_origen}</td>
                    {/*}
                    <td style={{ overflowWrap: "break-word" }}>{c.tipo_ambiente}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.tipo_emision}</td>
                    {*/}
                    <td style={{ overflowWrap: "break-word" }}>{c.razon_social}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.nombre_comercial}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.ruc}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.establecimiento}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.punto_emision}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.secuencial}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.establecimiento_ce_origen}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.secuencial_ce_origen}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.direccion_matriz_emisor}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.fecha_emision}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.direccion_establecimiento}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.contribuyente_especial}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.obligado_contabilidad}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.razon_social_comp_sr_trans}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.moneda}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.gr_fecha_ini_transporte}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.gr_fecha_fin_transporte}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.numero_autorizacion}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.fecha_autorizacion}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.clave_acceso}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.correo_electronico1}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.correo_electronico2}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.contador_reenvio}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.transaccion_origen}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.estado_aprobacion}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.fecha_aprobacion}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.usuario_ingreso}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.ubicacion_ingreso}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.ace_cab_ce_codigo}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.punto_emision_ce_origen}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.fecha_emision_origen}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.identificacion}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.direccion}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.total_sin_impuestos}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.rise}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.valor}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.fe_guia_remision}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.fe_total_descuento}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.fe_propina}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.fe_importe_total}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.cr_periodo_fiscal}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.gr_placa}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.nc_motivo}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.comprobante_estado}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.estado}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.usuario_aprobacion}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.observacion_aprobacion}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.fecha_estado}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.observacion_estado}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.fecha_ingreso}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.usuario_modificacion}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.fecha_modificacion}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.ubicacion_modificacion}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.age_licenc_codigo}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.ace_cab_ce_age_licenc_codigo}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.age_tip_id_codigo}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.valor_retencion_iva}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.valor_retencion_renta}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.codigo_reembolso}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.total_base_imponible_reembolso}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.total_impuestos_reembolso}</td>
                    <td style={{ overflowWrap: "break-word" }}>{c.total_comprobantes_reembolso}</td>
                  </tr>
              ))}
              </tbody>
            </table>
          </div>

        </div>
      </div>
  );
}

export default App
