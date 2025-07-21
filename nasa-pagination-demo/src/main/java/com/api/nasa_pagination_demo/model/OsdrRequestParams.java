package com.api.nasa_pagination_demo.model;

/**
 * Parámetros para consultar archivos de estudios NASA.
 * Incluye:
 * - studyIds: IDs de estudios (ej. 137,87-95,153.2)
 * - page: número de página (empieza desde 0)
 * - size: cantidad de resultados por página (máximo 25 según OSDR API)
 * - allFiles: incluir o no archivos ocultos
 */
public class OsdrRequestParams {

    private String studyIds = "137";
    private int page = 0; // ✅ Página empieza en 0 según la documentación oficial
    private int size = 20; // 🔧 Asegúrate que el servicio respete el máximo de 25
    private boolean allFiles = false;

    public OsdrRequestParams() {}

    public OsdrRequestParams(String studyIds, int page, int size, boolean allFiles) {
        this.studyIds = studyIds;
        this.page = Math.max(page, 0); // mínimo 0
        this.size = (size > 0 && size <= 25) ? size : 20; // OSDR recomienda máximo 25
        this.allFiles = allFiles;
    }

    public String getStudyIds() {
        return studyIds;
    }

    public void setStudyIds(String studyIds) {
        this.studyIds = studyIds;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = Math.max(page, 0);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = (size > 0 && size <= 25) ? size : 20;
    }

    public boolean isAllFiles() {
        return allFiles;
    }

    public void setAllFiles(boolean allFiles) {
        this.allFiles = allFiles;
    }
}