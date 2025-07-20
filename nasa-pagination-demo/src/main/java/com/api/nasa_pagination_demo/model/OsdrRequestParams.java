package com.api.nasa_pagination_demo.model;

/**
 * Represents the input parameters for querying NASA's OSDR file endpoint.
 * Supports pagination, visibility, and cursor-based filtering for study-specific file data.
 *
 * Fields include:
 * - studyIds: Identifies which NASA studies to query
 * - page: Specifies the page number for paginated results
 * - size: Limits the number of results per page
 * - allFiles: Toggles visibility for hidden or non-public files
 * - cursorDateCreated: Filters files created after this timestamp (for cursor-based pagination)
 *
 * Defaults are provided for sensible fallbacks, and setters include basic validation.
 */
public class OsdrRequestParams {

    // Comma- or dash-separated study identifiers (e.g., "87-95", "137,153.2")
    private String studyIds = "137";

    // Page number for pagination (default: 1)
    private int page = 1;

    // Number of results per page (default: 20; valid range: 1–100)
    private int size = 20;

    // Whether to include hidden or non-public files (default: false)
    private boolean allFiles = false;

    // Cursor-based pagination: timestamp in seconds with decimals (e.g. 1686100768.452)
    private Double cursorDateCreated;

    public OsdrRequestParams() {}

    public OsdrRequestParams(String studyIds, int page, int size, boolean allFiles, Double cursorDateCreated) {
        this.studyIds = studyIds;
        this.page = Math.max(page, 0);
        this.size = (size > 0 && size <= 100) ? size : 20;
        this.allFiles = allFiles;
        this.cursorDateCreated = cursorDateCreated;
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
        this.size = (size > 0 && size <= 100) ? size : 20;
    }

    public boolean isAllFiles() {
        return allFiles;
    }

    public void setAllFiles(boolean allFiles) {
        this.allFiles = allFiles;
    }

    public Double getCursorDateCreated() {
        return cursorDateCreated;
    }

    public void setCursorDateCreated(Double cursorDateCreated) {
        this.cursorDateCreated = cursorDateCreated;
    }
}