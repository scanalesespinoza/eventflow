# Prompt for Codex – Iteration 1 · Metrics dashboard (MVP)

## Objective (business vision)
As an administrator I want a fast, lightweight metrics dashboard that shows the key activity of the site at a glance so I can make decisions without visiting multiple screens. It must load quickly, be clear, and avoid exposing PII.

## Scope – implementation (application code)

1. Display/admin route → `metrics`
   - Create or update the single dashboard view.
   - Show “Last updated: X min” based on the timestamp of the displayed data (not the system clock).
   - Brief skeleton loaders while data is fetched.

2. Global range selector (applies to the entire dashboard)
   - Options: Today / Last 7 days / Last 30 days / Entire event.
   - Use the event time zone.
   - Changing the range refreshes cards and tables without navigation and without blocking the UI.

3. Summary cards (top row)
   - “Registrations for my talks (range)” – Total within the selected range.
   - “Event visits (range)” – Total within the selected range.
   - “Homepage visits (range)” – Total within the selected range.
   - “User profile visits (range)” – Total within the selected range.
   - “CTAs (range)” – Three counters: Releases | Report issue | Ko-fi ☕.
   - Under each card, short explanatory text describing the metric (non-technical).
   - Empty state: show `0` plus the explanatory text (do not hide the card).

4. Essential tables (Top 10, no pagination)
   - “Talks with more registrations (range)” – Columns: Talk · Event · Registrations.
   - “Most visited events (range)” – Columns: Event · Visits.
   - “Most visited speakers (range)” – Columns: Speaker · Profile visits.
   - “Most visited stages (range)” – Columns: Stage · Event · Visits.
   - Sort descending by the main metric; maximum 10 rows.
   - Placeholder when empty: “Insufficient data for this range.”

5. Data adapters (read-only, no PII)
   - Connect to existing metrics sources and apply the selected range.
   - Map IDs to readable names (talk, event, speaker, stage) using existing services/domains.
   - Ensure correct aggregations per range for each card/table.
   - Performance: avoid N+1 queries; make a single read per refresh (reuse snapshot/cache if available).
   - Do not introduce new personal identifiers or sensitive data.

6. Presentation states and errors
   - Show placeholders and clear messages when data is unavailable.
   - Handle read errors gracefully (non-technical message; do not block the entire view).

7. Accessibility and responsive baseline
   - Provide accessible labels/ARIA on cards and tables.
   - Maintain a logical tab order.
   - Ensure correct behaviour on medium screens (laptops).
   - Add data-testids for QA (for example, `data-testid="metrics-card-registrations"`).

8. Performance (product budget)
   - Initial perceived load <300 ms with typical data.
   - Smooth range transitions with no jank.

## Scope – documentation (records to create)

D1) Functional definitions (metric dictionary)
- “Registrations for my talks”: Total confirmed registrations for talks within the range.
- “Event visits”: Sum of detail/listing views for each event within the range.
- “Homepage visits”: Views of the home page within the range.
- “User profile visits”: Views of the profile section (aggregated, no PII).
- “Most visited speakers”: Views of the speaker profile within the range.
- “Most visited stages”: Views of the stage (and its event) within the range.
- “CTAs”: Clicks on “Releases”, “Report issue”, “Ko-fi”.

D2) Dashboard usage guide (short README in `docs/`)
- Describe what each card/table shows.
- Explain how ranges and “Last updated” work.
- Enumerate common states (“Insufficient data …”).

D3) Copy/UX glossary
- Titles, card labels, and table headings.
- State messages (loaders, no data, non-technical error).

D4) Performance and privacy criteria
- Load budget (<300 ms).
- Confirmation of “No PII”.
- Good aggregation/read practices.

## Acceptance criteria (DoD)

- Code -
- CA1: Cards show correct totals for the selected range.
- CA2: Top-10 tables sorted by their metric; max 10 rows; placeholder when empty.
- CA3: Changing the range refreshes cards and tables consistently and smoothly.
- CA4: “Last updated” is based on the displayed data and is readable (“X min”).
- CA5: Initial load <300 ms with typical data; transitions without blocking.
- CA6: No PII in the UI; minimum accessibility (ARIA, tab order, contrast).

- Docs -
- CA7: Metric dictionary updated.
- CA8: Brief dashboard usage guide available in `docs/`.
- CA9: Copy/UX reference documented for consistency.

## Functional tests (user/operations)
- Switching between Today / 7 days / 30 days / Entire event updates figures consistently.
- Range without activity in a category shows “Insufficient data” only in that table.
- Validate sums and totals per range.
- Validate baseline accessibility (tab order, labels) and laptop responsiveness.
- Confirm “Updated X min ago” changes after a refresh.

## Out of scope (iteration 1)
- “View” actions to detail, search, and export screens (iteration 2).
- Trends (%Δ), comparisons, and peaks (iteration 3).
- Additional scenario/stage/speaker insights (iteration 4).
- Extended CTA insights (historical means/deviations) (iteration 5).
- Data health module (iteration 6).
