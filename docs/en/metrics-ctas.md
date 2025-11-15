# CTA metrics

## Definitions and formulas
- **CTAs (range):** Sum of clicks on Releases, Report Issue, and Ko-fi within the selected range.
- **Daily average (per CTA and total):** `sum_in_range / days_in_range`.
- **Standard deviation (simple):** Population method over the daily totals within the range.
- **Peaks:**
  - *Option A (default):* Top 3 totals within the range.
  - *Option B (configurable):* Total ≥ average + 2 × standard deviation.
- **Time zone:** Use the event time zone to group by day.

## Presentation rules
- Table ordered by descending date.
- "Peak" badges with tooltip "Peak according to the rule configured for this range.".
- Show "No data to export in this range." placeholders and messages when applicable.

## Links
- URLs configurable through `links.releases-url`, `links.issues-url`, and `links.donate-url`.
- Recommended security attributes: `target="_blank"` + `rel="noopener"`.

## Privacy and performance
- Expose only aggregated data; never include PII.
- Reuse the snapshot/metrics cache to avoid longer load times.
