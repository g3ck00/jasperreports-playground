import axios from "axios";

const API = "http://localhost:8080/api/comprobantes";


export const getComprobantes = () => {
    return axios.get(API);
};

export const buscarComprobante = (
    codigo,
    ageLicencCodigo
) => {
    return axios.get(`${API}/busqueda`, {
        params: {
            codigo,
            ageLicencCodigo
        }
    });
};

export const generarReporte = (codigo, ageLicencCodigo) => {
    return `${API}/reporte?codigo=${codigo}&ageLicencCodigo=${ageLicencCodigo}&formato=pdf`;
};