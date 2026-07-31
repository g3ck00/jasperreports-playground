import { generarReporte } from "../services/comprobanteService";

export default function ComprobanteTable({ comprobantes }) {

    const abrirReporte = (
        codigo,
        ageLicencCodigo
    ) => {

        window.open(generarReporte(codigo, ageLicencCodigo),
            "_blank"
        );
    };


    return (
        <div className="table-responsive mt-4">
            <table className="table table-bordered table-striped table-hover">
                <thead className="table-dark">
                <tr>
                    <th>Código</th>
                    <th>Age Licencia</th>
                    <th>Acción</th>
                </tr>
                </thead>

                <tbody>

                {
                    comprobantes.map((c) => (
                        <tr key={`${c.id.codigo}-${c.id.ageLicencCodigo}`}>
                            <td>{c.id.codigo}</td>
                            <td>{c.id.ageLicencCodigo}</td>

                            <td>
                                <button
                                    className="btn btn-primary btn-sm"
                                    onClick={() =>
                                        abrirReporte(c.id.codigo, c.id.ageLicencCodigo)
                                    }
                                >
                                    Generar reporte
                                </button>
                            </td>
                        </tr>
                    ))
                }

                </tbody>
            </table>
        </div>
    );
}