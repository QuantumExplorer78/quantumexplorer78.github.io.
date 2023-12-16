const SNOWFLAKE_COUNT = 100;

function initSnowfall() {
    for (let i = 0; i < SNOWFLAKE_COUNT; i++) {
        snowflakes.push(createSnowflake());
    }

    drawSnowfall();
}
