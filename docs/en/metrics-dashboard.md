# Metrics Dashboard

## Navigation map
- **Talks:** each row links to the admin view of the talk at `/private/admin/events/{eventId}/edit` with the specific talk in context.
- **Events:** rows navigate to `/private/admin/events/{eventId}/edit`.
- **Speakers:** rows navigate to the speaker administration view at `/private/admin/speakers` focused on the selected speaker.
- **Stages:** rows navigate to `/private/admin/events/{eventId}/edit` with the stage highlighted.
- The dashboard preserves the selected time range and event filter when navigating to and returning from these pages.

## Filters and segmentation
- The **Event**, **Stage**, and **Speaker** filters combine with the selected date range.
- The event filter limits the available stages; if an event has no stages, show a disabled select labeled "No stages available".
- The speaker filter restricts talks and metrics associated with that presenter.

## Context persistence
- Filters and range are represented with readable query params: `range`, `event`, `stage`, and `speaker`.
- When navigating to detail views via "View", keep these parameters so users return to the same state.

## Copy and UX
- Filter labels: "Event", "Stage", "Speaker".
- Placeholders: "All", "Search speaker…", and "Insufficient data for this range/segment".
- Button: "Copy summary". Toast on copy: "Summary copied".

## Copied summary format
- Template: `Range: <range>\nEvent: <event>\nStage: <stage>\nSpeaker: <speaker>\nEvents viewed: <n>\nTalks viewed: <n>\nRegistrations: <n>\nStage visits: <n>\nLast updated: <timestamp>`
- Include only aggregated totals—never PII.

## Export specification
- Export only the rows and columns currently visible in the table, respecting range, search terms, and ordering.
- Columns per table:
  - **Talks:** `Talk`, `Event`, `Registrations`
  - **Events:** `Event`, `Visits`
  - **Speakers:** `Speaker`, `Profile visits`
  - **Stages:** `Stage`, `Event`, `Visits`
- File name format: `metrics-<table>-<range>-<YYYYMMDD-HHMM>.csv` using the event time zone.
- CSV exports never include personal identifiable information.

## Table actions copy
- Buttons: `View`, `Export CSV`
- Placeholder: `Search…`
- Disabled export message: `No data to export in this range.`
- Toast: `CSV downloaded`

## Trends
- Each card and table row can display a trend badge with the variation versus the previous period.
- Calculation logic and rules are described in `metrics-trends.md`.

## QA / checklist
- Verify that applying range, search, and ordering options is reflected in the exported CSV.
- Clicking `View` opens the correct admin page and preserves context when returning.
- When a table has no data, the export button is disabled and shows the no-data message.
- Keyboard navigation reaches all `View` and `Export CSV` buttons, and aria labels describe the destination.
- Confirm CSV files contain no personal identifiers such as emails or personal IDs.

## Data health and auto-refresh

### Data health rules
- Today: stale if the snapshot age exceeds 2 minutes.
- Last 7 days: stale if > 15 minutes.
- Last 30 days: stale if > 30 minutes.
- Entire event: stale if > 60 minutes.
- "No data": all cards at 0 and all tables empty after applying filters/range.

### Meaning of "Last updated"
- Derived from the timestamp of the data snapshot, never the client clock.
- Displayed as relative time: "2 min ago", "45 s ago"; use "just now" for <1 s.

### Auto-refresh
- Default interval: 5 s (configurable).
- Coalesce requests: if a refresh is in progress, do not start another.
- Re-render conditionally based on a data hash.
- Notify refresh failures only once until the next successful refresh.
- Controls: `Pause/Resume` (`data-testid="metrics-refresh-toggle"`, `aria-pressed`) and `Refresh now` (`data-testid="metrics-refresh-now"`, throttle 2 s).

### Copy and UX (status)
- States: "OK", "Stale", "No data".
- Messages: "Could not refresh; will retry", "No data for this range/segment".
- Accessibility: `aria-live` on status and "Last updated"; buttons expose `aria-pressed` and tooltips.

### Privacy and performance
- No PII in messages or displayed data.
- Avoid flicker by reloading only when the data hash changes.
